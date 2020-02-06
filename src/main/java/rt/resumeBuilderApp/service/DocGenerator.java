/*******************************************************************************
 * Copyright 2020 Raviteja Chowdari
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package rt.resumeBuilderApp.service;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.*;
import rt.resumeBuilderApp.entities.Resume;


/**
 * Created by Raviteja  on 11-01-2020.
 * Takes the resume object from resume builder class and generates the document
 */
public class DocGenerator {

    private String templatePath;
    private String outPath;
    private Resume resumeObj;
    private XWPFDocument document;
    //private Map<String,String> settings;

    public DocGenerator(String templatePath,String outPath,Resume resumeObj){
        this.templatePath= templatePath;
        this.outPath = outPath;
        this.resumeObj = resumeObj;
    }

    //top level method that takes care of IO operations in generating resume
    public void generateResume() throws Exception{
        try {
            document = new XWPFDocument(new FileInputStream(templatePath));
            constructDoc();
            OutputStream fileOut = new FileOutputStream(outPath);
            document.write(fileOut);
            System.out.println("File created");

        }catch(IOException |IllegalAccessException|IntrospectionException|InvocationTargetException ex) {
            ex.printStackTrace();
            throw new Exception("Exception while writing word document file, see logs for information");
        }catch(Exception e) {
            e.printStackTrace();
            throw new Exception(e.getClass()+" has occurred ");
        }
    }


     /*
        *constructs the document step by step from given template
        * remove $ and {} , split the varaible using '.' and invoke the value
        * */
    public void constructDoc() throws IllegalAccessException, IntrospectionException, InvocationTargetException {

        for(XWPFTable table :  document.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    String cellValue = cell.getText();
                    System.out.println(cellValue);
                    if(cellValue.startsWith("$")){
                        String style = cell.getParagraphs().get(0).getStyleID();
                        String fieldVal = cellValue.replaceAll("\\$|\\{|\\}","");
                        Object replaceVal = getFieldValue(resumeObj,fieldVal);
                        if(replaceVal instanceof List){
                            String combineString = combiner((List)replaceVal);
                            replaceCellValue(cell,combineString,style);
                        }else {
                            replaceCellValue(cell, replaceVal.toString(),style);
                        }
                    }
                }
            }
        }
        //handle others , for each key in others create separate table
        generateExtra(resumeObj.getOthers());

        //create final declaration
        if(resumeObj.getDeclaration()!=null && resumeObj.getDeclaration().length()>0) {
            XWPFParagraph finalPara = document.createParagraph();
            XWPFRun finalRun = finalPara.createRun();
            finalRun.setText("\n" + resumeObj.getDeclaration());
            finalRun.setText("\n" + resumeObj.getName());
        }
    }

    /*
     *generate new tables for extra information provided in others field
     *parses others field in resume and adds new row for every key
     *if a map is encountered as a value , parses values as key value pairs
     *check only for one sub level child , will not do recursion
     * */
    private void generateExtra(Map<String,Object> userMap){
        XWPFTable refTable = document.getTables().get(0);
        for(Map.Entry<String,Object> entry: userMap.entrySet()){
            XWPFTable newTable = document.createTable();
            newTable.setWidth(refTable.getWidth());
            XWPFTableCell titleCell = newTable.getRow(0).getCell(0);
            XWPFRun cellRun = titleCell.getParagraphs().get(0).createRun();
            cellRun.setBold(true);
            cellRun.setText(headerFormatter(entry.getKey()));
            XWPFTableRow valueRow = newTable.createRow();
            Object value = entry.getValue();
            if(value instanceof List){
                String combinedString = combiner((List)value);
                valueRow.getCell(0).setText(combinedString);
            }else if(value instanceof Map){
                Map<String,Object> childMap = (Map<String,Object>) value;
                for(Map.Entry<String,Object> childEntry : childMap.entrySet()){
                    String keyVal = headerFormatter(childEntry.getKey());
                    String propertyVal = childEntry.getValue().toString();
                    if(childEntry.getValue() instanceof List){
                        propertyVal = propertyVal.replaceAll("\\[|\\]","");
                    }
                    valueRow.getCell(0).setText( keyVal + " : " + propertyVal + "\n");
                }
            }else{
                valueRow.getCell(0).setText(value.toString());
            }
            document.createParagraph().createRun().setText("\n");
        }
    }

    //helper method to replace ${value} in the cells with resume entries
    private void replaceCellValue(XWPFTableCell cell,String replaceString,String style){
        cell.removeParagraph(0);
        XWPFRun cellRun = cell.addParagraph().createRun();
        cellRun.setStyle(style);
        cellRun.setText(replaceString);
    }

    //helper method to access the getter method of the given field
    private Object getFieldValue(Object parentObj, String fieldName) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        PropertyDescriptor pd = new PropertyDescriptor(fieldName , parentObj.getClass());
        return pd.getReadMethod().invoke(parentObj);
    }

    //combine values in a List to combined string separated by new lines
    private String combiner(List list){
        StringBuilder combine = new StringBuilder();
        for(Object item : list){
            combine.append(item);
            combine.append("\n");
        }
        return combine.toString();
    }

    //set header format - to capitalize all first letter
    //also removes underscores in the fields
    private String headerFormatter(String header){
        String[] words = header.split("_|-|#");
        StringBuilder headerBuilder = new StringBuilder();
        for(String word: words){
            headerBuilder.append(""+Character.toUpperCase(word.charAt(0))+word.substring(1)+" ");
        }
        return headerBuilder.toString();

    }

}

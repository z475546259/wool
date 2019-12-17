package com.zzq.test;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DOM生成与解析XML文档
 */
public class DomDemo  {
    public static void main(String[] args) {
        DomDemo d = new DomDemo();
        d.parserXml("F:\\idea workspace\\yunshanchong\\src\\main\\resources\\public.xml");
    }

    public List<String> parserXml(String fileName) {
        List<String> keys = new ArrayList<>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);

            Node resources = document.getFirstChild();
            NodeList nodeList = resources.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
//                System.out.println(node.getNodeName() + ":" + node.getTextContent());

                NamedNodeMap nodeMap = node.getAttributes();
                if (nodeMap!=null){
                    if(nodeMap.item(2).getNodeValue().equals("string")){
                        String str = nodeMap.item(1).getNodeValue();
//                        System.out.println(str);
                        keys.add(str);
                    }
//                    for(int j = 0; j<nodeMap.getLength(); j++){
//                        Node nodeM = nodeMap.item(j);
//                        //通过Node对象的getNodeName()和getNodeValue()方法获取属性名和属性值
//                        System.out.println(j+nodeM.getNodeName()+":"+nodeM.getNodeValue());
////                    System.out.println(nodeM.getNodeValue());
//                    }
                }

//                System.out.println(node.getNodeValue());
//                NodeList employeeInfo = employee.getChildNodes();
//                for (int j = 0; j < employeeInfo.getLength(); j++) {
//                    Node node = employeeInfo.item(j);
//                    NodeList employeeMeta = node.getChildNodes();
//                    for (int k = 0; k < employeeMeta.getLength(); k++) {
//                        System.out.println(employeeMeta.item(k).getNodeName() + ":" + employeeMeta.item(k).getTextContent());
//                    }
//                }
            }
            System.out.println("解析完毕");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return keys;
    }
}
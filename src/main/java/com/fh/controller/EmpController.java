package com.fh.controller;

import com.fh.common.ServiceResponse;
import com.fh.model.Emp;
import com.fh.service.EmpService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("emp")
public class EmpController {
    @Autowired
    private EmpService empService;

    @RequestMapping("write")
    public ServiceResponse writeXMLDemo (){
        /*
         *生成XML文档的大致步骤
         *1：创建一个Document对象表示一个空白文档。
         *2：向Document对象中添加根元素
         *3：按照预定的XML文档结构从根元素开始逐级添加子元素
         *4：创建XMLWriter
         *5：通过XMLWriter将Document写出以形成XML文档
         */
        List<Emp> list = empService.query();
        try{
            //1.创建一个Document对象表示一个空白文档。
            Document doc= DocumentHelper.createDocument();

            /*
             * Document 提供了添加根源的方法
             * Element addElement(String name)
             * 向当前文档中添加给定名字的根元素，并将它以Element
             * 实例返回，以便继续向根元素中追加内容
             * 需要注意，该方法只能调用一次，因为一个文档中只能有一个根元素
             */

            Element root=doc.addElement("list");
            /*
             * Element提供了向标签中添加信息的相关方法：
             *
             * Element addElement(String name)
             * 添加给定名字的子标签并将其返回
             *
             * Element addText(String text)
             * 添加文本信息,返回值为当前标签，这样做便于连续追加操作
             *
             * Element addAttribute(String name,String value)
             * 添加给定名字及对应值的属性，返回值为当前标签
             *
             *
             * 将每一个emp实例以一个<emp>标签的形式 添加到根标签<list>中
             */
            for(Emp emp:list){
                Element empEle =root.addElement("emp");
                //添加<name>
                Element nameEle=empEle.addElement("name");
                nameEle.addText(emp.getName());
                //添加<age>
                Element ageEle=empEle.addElement("age");
                ageEle.addText(String.valueOf(emp.getAge()));
                //添加<gender>
                Element genderEle=empEle.addElement("gender");
                genderEle.addText(emp.getGender());
                //添加<salary>
                Element salaryEle=empEle.addElement("salary");
                salaryEle.addText(String.valueOf(emp.getSalary()));
                //添加id属性
                empEle.addAttribute("id",String.valueOf(emp.getId()));
            }
            //4.创建XMLWriter
            XMLWriter writer=new XMLWriter(new FileOutputStream("D:/myemp.xml"),
                    OutputFormat.createPrettyPrint());
            //5.通过XMLWriter将Document写出以形成XML文档
            writer.write(doc);
            System.out.println("写出完毕！");
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return ServiceResponse.success("success");
    }

    @RequestMapping("read")
    public ServiceResponse readXMLDemo(){
        /*
         * 解析XML的大致步骤
         * 1:创建SAXReader
         * 2:使用SAXReader读取XML文档并生成Document对象
         *   这一步就是DOM解析耗时耗资源的地方 因为要先将XML文档内容全部读取完毕
         *   并生成一个Document对象.
         * 3：通过Document获取根元素
         * 4：按照XML文档的结构从根元素开始 逐级获取子元素以达到遍历XML文档数据的目的.
         */
        /*
         * 该集合用于保存从emplist.xml文档中解析出来的员工信息
         */
        List<Emp> list=new ArrayList<Emp>();
        try{
            //1.创建SAXReader
            SAXReader reader=new SAXReader();

            //2.使用SAXReader读取XML文档并生成Document对象
            Document doc=reader.read(new FileInputStream("D:/myemp.xml"));

            /*
             * Document提供了获取根元素的方法：
             * Element getRootElement()
             *
             * Element 的每一个实例用于表示XML文档中的一个元素（一对标签）
             *
             * Element 提供了获取其元素的相关信息的方法：
             *
             * String getName()
             * 获取标签的名字
             *
             * String getText()
             * 获取标签中间的文本信息（开始标签与结束标签之间的文本）
             *
             * Elment element(String name)
             * 获取指定名字的子元素
             *
             * List elements(String name)
             * 获取所有同名子元素
             *
             * Attribute attribute(String name)
             * 获取指定名字的属性
             */
            Element root=doc.getRootElement();
            /*
             * 获取根元素<list>下面所有子元素
             * 若干个<emp>元素.而每一个<emp>元素也是使用Element实例表示，
             * 并存入集合后返回.
             */
            List<Element> empList= root.elements();
            for(Element empEle:empList){
                //获取员工姓名
                Element nameEle=empEle.element("name");
                String name=nameEle.getText();
                //获取age
                int age=Integer.parseInt(empEle.elementText("age"));
                /*
                 * Element有一个可以快速获取子标签中间的文本方法：
                 * String elementText(String name);
                 * 例如：
                 * empEle.elementText("gender")
                 * 等同于
                 * empEle.element("gender").getText()
                 *
                 */
                //获取gender
                String gender=empEle.elementText("gender");
                //获取salary
                int salary=Integer.parseInt(empEle.elementText("salary"));
                int id=Integer.parseInt(empEle.attributeValue("id"));
                Emp emp=new Emp(id,name,age,gender,salary);
                list.add(emp);
            }
            System.out.println("解析完毕！");
            for(Emp e:list){
                System.out.println(e);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        empService.insert(list);
        return ServiceResponse.success(list);
    }

@   RequestMapping("query")
    public List<Emp> query(){
        return empService.query();
    }


    @RequestMapping("add")
    private ServiceResponse add(){
        return empService.add();
    }
}

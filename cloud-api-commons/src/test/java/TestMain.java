

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {
        List<DemoData> data=data("ppppp");
        //WriteExcel(1,"aa",data);
        test2();
    }
    public void test1(){
        try {
            Scanner in = new Scanner(new File("E:/cloud_manufacture_sm2020.sql"));

            while (in.hasNextLine()) {

                String str = in.nextLine();

                String[] arr = str.split("ROW_FORMAT = Compact;");

                Float[] result = new Float[arr.length - 1];
                for (int i = 0; i < result.length; i++) {
                    result[i] = Float.parseFloat(arr[i + 1]);
                }

                System.out.println(Arrays.toString(result));
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void test2()
    {
          String readPath ="E:/cloud_manufacture_sm2020.sql";
        String s="";
        StringBuffer buffer = new StringBuffer();
        String fileName = "E://repeatedWrite.xlsx";
        File file = new File(fileName);
        ExcelWriter excelWriter = null;

        FileInputStream inputStream = null;
        try {

            //指定用哪个class去写
            excelWriter=EasyExcel.write(file,DemoData.class).build();
            inputStream = new FileInputStream(readPath);
            byte[] bytes = new byte[1024];
            int len =0;
            while ((len=inputStream.read(bytes))!=-1){
               s = new String(bytes, 0, len);
                buffer.append(s);
            }
            String[] arrays = buffer.toString().split("ROW_FORMAT = Compact;");
            int count=0;
            for (int i=0;i<arrays.length;i++){
                count++;
                System.out.println("*************************************"+count);
                System.out.println(arrays[i]+"ROW_FORMAT = Compact;");
                //写进不同的sheet

                List<DemoData> data=data(arrays[i]+"ROW_FORMAT = Compact;");
                WriteSheet writeSheet = EasyExcel.writerSheet(i, "模板"+i).build();
                excelWriter.write(data,writeSheet);
            }
            //System.out.println(buffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(excelWriter != null){
                excelWriter.finish();  
            }
        }


    }
    public static void WriteExcel(int sheetNo,String sheetName,List data){
        String fileName = "E://repeatedWrite.xlsx";
        ExcelWriter excelWriter = null;
        try {
            //指定用哪个class去写
            excelWriter=EasyExcel.write(fileName,DemoData.class).build();
            //写进不同的sheet
            WriteSheet writeSheet = EasyExcel.writerSheet(sheetNo, sheetName).build();
            excelWriter.write(data,writeSheet);
        }
        finally {
            if(excelWriter!=null){
                excelWriter.finish();
            }
        }

    }
    private static List<DemoData> data(String st) {
        List<DemoData> list = new ArrayList<DemoData>();
            DemoData data = new DemoData();
            data.setSting(st);
            list.add(data);
        return list;
    }

}

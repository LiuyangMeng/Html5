package ztree;

import java.util.ArrayList;
import java.util.List;

public class ztreeTest{  
    public List<Competence> getAllAuthorize(){  
        List<Competence> authorizes = new ArrayList<Competence>() ;  
                Competence authorize = new Competence() ;  
                authorize.setId(100) ;  
                authorize.setName("父1") ;  
                authorize.setIsParent(1) ;  
                authorize.setOpen(0) ;  
                authorize.setpId(0) ;  
                authorizes.add(authorize) ;
                Competence authorize1 = new Competence() ;  
                authorize1.setId(101) ;  
                authorize1.setName("子1") ;  
                authorize1.setIsParent(0) ;  
                authorize1.setOpen(0) ;  
                authorize1.setpId(100) ;  
                authorize1.setUrl("http://www.baidu.com");
                authorizes.add(authorize1) ;
                Competence authorize2 = new Competence() ;  
                authorize2.setId(102) ;  
                authorize2.setName("子1") ;  
                authorize2.setIsParent(0) ;  
                authorize2.setOpen(0) ;  
                authorize2.setpId(100) ; 
                authorize2.setUrl("http://www.qq.com");
                authorizes.add(authorize2) ;
        return authorizes ;  
    }  
    public String getJsonData(){  
        List<Competence> list = getAllAuthorize() ;  
        StringBuffer json = new StringBuffer("[") ;  
        String data = "" ;  
        int length = list.size() ;  
        for(int i=0;i<length;i++){  
            json.append("{id:" + list.get(i).getId() + ",") ;  
            json.append("pId:" + list.get(i).getpId() + ",") ;  
            json.append("name:\"" + list.get(i).getName() + "\",") ;  
            if(list.get(i).getIsParent() != 0){  
                json.append("isParent:" + list.get(i).getIsParent() + ",") ;  
            }  
            if(list.get(i).getOpen() != 0){  
                json.append("open:" + list.get(i).getOpen() + ",") ;  
            }  
            if(list.get(i).getUrl() != null){  
                json.append("url:\"" + list.get(i).getUrl() + "\",") ;  
            }  
            data = json.substring(0,json.lastIndexOf(",")) + "}," ;  
            json = new StringBuffer(data) ;  
        }  
        data = json.substring(0,json.length()-1) + "]" ;  
        System.out.println(data);  
        return data ;  
    }  
    public static void main(String[] args) {  
        new ztreeTest().getJsonData() ;  
    }  
}  
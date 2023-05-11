package application;
import java.util.*;
public class DataClass {
private static List<Personne> importList;
private List<Personne> exportList;
public DataClass()
{
this.importList=new ArrayList <Personne>();
this.importList.add(new Personne("Sami","BenAli","sami@exemple.com"));
this.importList.add(new Personne("Alia","BenSalah","ali@exemple.com"));
this.importList.add(new Personne("Ali","BenSalah","ali@exemple.com"));
this.exportList=new ArrayList <Personne>();
}
public static List <Personne> getImportList() {
return importList;
}
public List <Personne> getExportList() {
return exportList;
}
public void setExportList(List <Personne> exportList) {
this.exportList.addAll(exportList);
for(Personne p:this.exportList)
System.out.println(p);

}
}
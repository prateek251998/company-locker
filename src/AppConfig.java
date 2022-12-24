import java.io.FilenameFilter;
import java.util.*;
import java.io.File;
public class AppConfig implements FilenameFilter {
    private final String _AppName;
    private final String _AppDeveloper;
    private final String _AppDeveloperDetails;
    private boolean _appState;
    private static Scanner Input;
    protected Screen welcomeScreen;
    protected Screen FileManipulation;
    private Screen currentScreen;
    private int UserSelection;
    private static ArrayList<String> _FileList ;
    public AppConfig(String appName,String AppDeveloperName,String AppDeveloperDetails) {
        _AppName = appName;
        _AppDeveloper = AppDeveloperName;
        _AppDeveloperDetails = AppDeveloperDetails;
        _appState=true;
        initFileList("root");
        welcomeScreen=new Screen(new ArrayList<>(Arrays.asList("View Files","Modify File List","Exit App")),this,"WelcomeScreen");
        FileManipulation =new Screen(new ArrayList<>(Arrays.asList("Add File","Delete A File","Search","Go Back")),this,"FileHandling");
    }
    void printFiles()
    {
        if(_FileList.size() == 0)
        {
            System.out.println("File List Empty");
            return;
        }
        int i=0;
        Collections.sort((_FileList));
        while (i <  _FileList.size())
        {
            System.out.println((i+1)+":"+"["+_FileList.get(i)+"]");
            i=i+1;
        }
    }
    void initFileList(final String str)
    {
        System.out.println("Initializing File LIst");
        if(setDir("root") == false)
        {
            System.out.println("Directory Already Exists");
        }else{
            System.out.println("Directory Created");
        }
        refereshArrayList(str);

    }
    void refereshArrayList(String str)
    {
        if(_FileList != null){
            _FileList.clear();
        }
        _FileList = new ArrayList<>();
        File Folder = new File(getClass().getClassLoader().getResource(".").getPath()+str);
        File[] listOfFiles = Folder.listFiles();

        System.out.println("List Of Files: "+listOfFiles.length);
        for (File file : listOfFiles) {
            //  if (file.isFile()) {
            System.out.println("Got "+file.getName());
            _FileList.add(file.getName());
            //  }
        }
    }
   
    void printScreen()
    {
        currentScreen.printScreen();
    }
    void printFile(File[] files)
    {
        if (files.length <= 0){
            System.out.println("No Files Found");
            return;
        }
        for (File f :files) {
            System.out.println("Found File: "+f.getName());
        }

    }
    File[] searchFile(String str){

        File f = new File(getClass().getClassLoader().getResource("./root").getPath());
    //    System.out.println("Searching at File Path: "+f.getPath());
        File[] matchingFiles = f.listFiles((dir, name) -> (name.startsWith(str)));
        return matchingFiles;
    }
    void addFile(String fileName)
    {

        if(setDir("root/"+fileName)){
            System.out.println("[Info] Directory Created Successfully");
            _FileList.add(fileName);
        }else
        {
            System.out.println("[Error] File Already Exists Or can not be created");
        }
    }
    //getters
    boolean deleteFile(String str)
    {
        String file="root/"+str;
        if(isFile(file))
        {
            new File(getClass().getClassLoader().getResource(".").getPath()+file).delete();
            System.out.println("File Deleted");
            refereshArrayList("root");
            return true;
        }else{
            System.out.println("File Not Found");
            return false;
        }
    }
    boolean isFile(String fileName)
    {
        if(new File(getClass().getClassLoader().getResource(".").getPath()+fileName).exists())
        {
            return true;
        }else
        {
            return false;
        }
    }
    String getAppName()
    {
        return _AppName;
    }
    String getAppDeveloper()
    {
        return _AppDeveloper;
    }
    String getAppDeveloperDetails()
    {
        return _AppDeveloperDetails;
    }
    String getStr(String str)
    {
        Input = new Scanner(System.in)  ;
        System.out.println(str);
        String UserIn = Input.nextLine();
        return UserIn;
    }
    boolean getAppState()
    {
        return _appState;
    }
    ArrayList getFileList()
    {
        return _FileList;
    }
    int getUserSelection()
    {
        return UserSelection;
    }
    Screen getCurrentScreen(){
        return currentScreen;
    }
    void setScreen(Screen sc)
    {
        currentScreen = sc;
    }
    //setters
    boolean setDir(String filName)
    {
        try{
            if(new File(getClass().getClassLoader().getResource("").getPath()+filName).mkdirs()){
                return true;
            }else{
                return false;
            }
        }catch (Exception e)
        {
            System.out.println("[Error] Unable To Create The Root Folder");
            return false;

        }

    }
    void setUserSelection(int choice)
    {
        if(currentScreen == welcomeScreen)
        {
            UserSelection=choice;
        }else if(currentScreen == FileManipulation){
            UserSelection=(choice+10);
        }
    }

    void setAppState(boolean state)
    {
        _appState = state;
    }

    @Override
    public boolean accept(File dir, String name) {
        return false;
    }
}


public class Application extends AppConfig implements App{

    Application(String AppName,String DevName,String DevDetails)
    {
        super(AppName,DevName,DevDetails);
        System.out.println("Starting new app");
        StartApp();
    }
    @Override
    public void InitApp() {
        System.out.println("Initializing App..");
        setScreen(welcomeScreen);
    }
    @Override
    public void appLoop(){
        while(getAppState() ==true)
        {
            printScreen();
            setUserSelection(Integer.parseInt(getStr("Enter Your Choice: ")));
            switch (getUserSelection())
            {
                case 1:
                {
                    System.out.println("User Selected First");
                    printFiles();
                    break;
                }case 2:
                {
                    System.out.println("User Selected Second");
                    setScreen(FileManipulation);
                    break;
                }
                case 3:
                {
                    System.out.println("User chose To Exit the App");
                    setAppState(false);
                    break;
                }
                case 11:
                {
                    System.out.println("User Selected 11");
                    addFile(getStr("Enter File Name:"));
                    break;
                }
                case 12:
                {
                    System.out.println("User Selected 12");
                    deleteFile(getStr("Enter File Name:"));
                    break;
                }
                case 13:
                {
                    System.out.println("User Selected 13");
                    printFile(searchFile(getStr("Enter File Name to be searched")));
                    break;
                }
                case 14:
                {
                    System.out.println("User Selected 14");
                    setScreen(welcomeScreen);
                    break;
                }
                default:{
                    System.out.println("Invalid Choice,Please Select again");
                }
            }
        }
    }
}

import java.util.ArrayList;
import java.util.Arrays;

public class Screen extends UserInputStream {
    ArrayList<String> List;
    AppConfig config;
    String _ScreenName;
    Screen(ArrayList<String> list,AppConfig configuration,String screenName)
    {
        super(screenName);
        this.List =  list;
        _ScreenName=screenName;
        config=configuration;
    }
    void printScreen()
    {
       int i=0;
       System.out.println("[App Name: ( "+config.getAppName()+" ) ]");
       System.out.println("[App Developer: ( "+config.getAppDeveloper()+" ) ]");
       System.out.println("[App Developer Details: ( "+config.getAppDeveloperDetails()+" ) ]");
       while(i < List.size())
       {
          System.out.println((i+1)+": "+List.get(i));
          i=i+1;
       }
    }
    void addNewListItem(String item)
    {
        List.add(item);
    }

}

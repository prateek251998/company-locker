public interface App {

    void InitApp();
    default void StartApp()
    {
        InitApp();
        appLoop();
    }

    void appLoop();


}

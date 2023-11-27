import com.basejava.webapp.model.Resume;
import java.lang.reflect.InvocationTargetException;

public class MainReflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        var resumeClass = Resume.class;
        var resumeConstructor = resumeClass.getConstructor();
        var instance = resumeConstructor.newInstance();
        var method = resumeClass.getDeclaredMethod("toString");
        System.out.println(method.invoke(instance));
    }
}

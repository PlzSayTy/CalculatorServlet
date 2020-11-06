import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/add")
public class CalculatorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        StringBuffer jb = new StringBuffer();
        String line;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try{
            BufferedReader reader = httpRequest.getReader();
            while ((line = reader.readLine())!= null){
                jb.append(line);
            }
        }catch (Exception e){
            System.out.println("ERROR!!!");
        }
        JsonObject jsonObject = gson.fromJson(String.valueOf(jb), JsonObject.class);
        httpRequest.setCharacterEncoding("UTF-8");
        Integer firstNumber = jsonObject.get("firstNumber").getAsInt();
        String whatToDo = jsonObject.get("whatToDo").getAsString();
        Integer secondNumber = jsonObject.get("secondNumber").getAsInt();
        PrintWriter printWriter = httpResponse.getWriter();
        switch (whatToDo) {
            case "+":
                printWriter.print(gson.toJson(firstNumber + secondNumber));
                break;
            case "-":
                printWriter.print(gson.toJson(firstNumber - secondNumber));
                break;
            case "*":
                printWriter.print(gson.toJson(firstNumber * secondNumber));
                break;
            case "/":
                printWriter.print(gson.toJson(firstNumber / secondNumber));
                break;
        }
        httpResponse.setContentType("application/json;charset=utf-8");

    }

}

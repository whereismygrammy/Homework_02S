package pl.swiatek.homework_2.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class HelloController {

    @GetMapping("/workers")
    public String workersAction(Model model) {
        String numstr;
        String worker = "brak pracownika";
        Random random = new Random();
        int num = random.nextInt(30) + 1;
        if (num < 10) {
            numstr = "0" + num + "";
        } else {
            numstr = num + "";
        }
        String regex = "^" + numstr + ",.[A-z]+.[A-z]+";
        Pattern pattern = Pattern.compile(regex);
        List<String> workers = loadFile("/home/tom/IdeaProjects/Homework_02/src/main/resources/Workers.txt");

        for (String s : workers) {
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                worker = s;
            }
        }
        model.addAttribute("worker", "Pracownik: " + worker);
        return "workers";
    }

    public List<String> loadFile(String path) {
        List<String> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(path));
            while (sc.hasNext()) {
                list.add(sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}

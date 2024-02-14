package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

  @GetMapping("hello") // 웹에서 /hello 가 들어오면 해당 메서드 출력.
  public String hello(Model model) {
    model.addAttribute("data", "hello!!");
    return "hello"; // Resources/template의 hello를 찾음.
  }

  // template
  @GetMapping("hello-mvc")
  public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model) { // 외부에서 파라미터를 받음.
    model.addAttribute("name", name);
    return "hello-template"; // hello-template.html을 찾음.
  }

  // API
  @GetMapping("hello-string")
  @ResponseBody // http의 body부에 이 데이터를 직접 넣어주겠다는 의미.
  public String helloString(@RequestParam("name") String name) {
    return "hello " + name; // "hello spring" ->
	  // 뷰가 없이 바로 데이터로 나감. API 형식
  }

  //
  @GetMapping("hello-api")
  @ResponseBody
  public Hello helloApi(@RequestParam("name") String name) {
    Hello hello = new Hello();
    hello.setName(name);
    return hello; // *객체를 반환하면* json으로 반환됨.
  }

  static class Hello {

    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

//  public String getName() {
//    return name;
//  }
//
//  public void setName(String name) {
//    this.name = name;
//  }
}
}

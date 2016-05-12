
import static spark.Spark.*;

import spark.Spark;
import spark.ModelAndView;

import spark.template.mustache.MustacheTemplateEngine;

public class Main {

  public static void main(String[] args) {

    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");

    Spark.get(
            "/hello", (req, res) -> {

              return new ModelAndView(null, "home.mustache");
            },
            new MustacheTemplateEngine()
    );


  }

}

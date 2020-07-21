package jm.stockx.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
public class ViewController {
    private final String botUsername;

    @Autowired
    public ViewController(Environment environment) {
        botUsername = Objects.requireNonNull(environment.getProperty("telegramBot.botUsername")).replace("@", "");
    }

    @GetMapping(value = "/")
    public String indexPage() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        model.addAttribute("botUsername", botUsername);
        return "login";
    }

    @GetMapping(value = "/news")
    public String newsPage() {
        return "news";
    }

    @GetMapping(value = "/text-me-the-app")
    public String textAppPage() {
        return "text-me-the-app";
    }

    @GetMapping(value = "/portfolios")
    public String portfolioPage() {
        return "portfolios";
    }

    @GetMapping(value = "/how-it-works")
    public String howWorksPage() {
        return "how-it-works";
    }

    @GetMapping(value = "/help")
    public String helpPage() {
        return "help";
    }

    @GetMapping(value = "/sell")
    public String sellPage() {
        return "sell";
    }

    @GetMapping(value = "/reviews")
    public String reviewPage() {
        return "reviews";
    }

    @GetMapping(value = "/privacy")
    public String privacyPage() {
        return "privacy";
    }

    @GetMapping(value = "/terms")
    public String termsPage() {
        return "terms";
    }

    @GetMapping(value = "/jobs")
    public String jobsPage() {
        return "jobs";
    }

    @GetMapping(value = "/press")
    public String pressPage() {
        return "press";
    }
}


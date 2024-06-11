package hufs.sweepyswipe.controller;

import hufs.sweepyswipe.domain.TrashItem;
import hufs.sweepyswipe.service.TrashItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TrashItemController {
    @Autowired
    private TrashItemService trashItemService;

    @GetMapping("/guidebook")
    public String guidebook(Model model) {
        List<TrashItem> trashItems = trashItemService.findAll();
        model.addAttribute("trashItems", trashItems);
        return "guidebook";
    }
}

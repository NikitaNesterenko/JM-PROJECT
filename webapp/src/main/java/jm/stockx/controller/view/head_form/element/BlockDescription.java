package jm.stockx.controller.view.head_form.element;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;

@CssImport("./styles/css/descriptionBlock.css")
public class BlockDescription extends Div {
        Span leftPart;
        Span rightPart;
        String leftContent;
        String rightContent;

        public BlockDescription() {
            this("Colorway", " GYM RED/WHitE-BLACK");
        }

        public BlockDescription(String s1, String s2){
            leftContent = s1.toUpperCase();
            rightContent = s2.toUpperCase();
            initBlockDescription();
            initClass();
            add(leftPart, rightPart);
        }

        private void initBlockDescription() {
            leftPart = new Span(leftContent);
            rightPart = new Span(rightContent);
        }

        private void initClass() {
            this.addClassName("block_description_style");
            leftPart.addClassName("left_part_style");
            rightPart.addClassName("right_part_style");
        }

}

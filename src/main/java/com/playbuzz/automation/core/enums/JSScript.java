package com.playbuzz.automation.core.enums;

import com.playbuzz.automation.core.utils.FileUtils;

import java.io.File;

public enum JSScript {

    CLICK(FileUtils.readFile(new File("src/main/resources/js/click.js")
            .getAbsolutePath())),
    DRAG_AND_DROP(FileUtils.readFile(new File("src/main/resources/js/dragAndDrop.js")
            .getAbsolutePath()));

    private String script;

    JSScript(String script) {
        this.script = script;
    }

    public String getScript() {
        return script;
    }
}

package com.hampion.plugins.Managers;

import java.io.File;

public class FileManager {
    String location = "plugins/RankNChat";

    public void Startup() {
        // Checking location of file.
        File MainOutput = new File(location);
        if(!MainOutput.exists()) {
            MainOutput.mkdir();
        }
        // Checking location of player data.
        File PlayerData = new File(location + "/playerData");
        if(!MainOutput.exists()) {
            MainOutput.mkdir();
        }
    }
}

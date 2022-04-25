package com.anything.tacticool.view.util;

public enum SpriteConnectorEnum {
    GRASS("tile1.png"),
    HIGHLIGHTTILE("tilehl.png"),
    PLAYER("player.png"),
    ENEMY("enemy.png"),
    BULLET("bullet.png"),
    EXPLOSION("explosion.png");

    private final String filePath;

    private SpriteConnectorEnum(final String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}

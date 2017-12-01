package kklions.mazesolver.model;

/**
 * Class used to transfer data regarding configuration data for the maze solving screen'
 * This may be refactored based on conversations with Davin later
 *
 * Created by kliok002 on 11/28/17.
 */

public class MazeConfiguration {
    private int height;
    private int width;
    private String method;
    private int interval;

    public int getHeight() { return this.height; }

    public int getWidth() { return this.width; }

    public String getMethod() { return this.method; }

    public int getInterval() { return this.interval; }

    public static class Builder {
        private int height;
        private int width;
        private String method;
        private int interval;

        public Builder() {

        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setMethod(String method) {
            this.method = method;
            return this;
        }

        public Builder setInterval(int interval) {
            this.interval = interval;
            return this;
        }

        public MazeConfiguration build() {
            MazeConfiguration mazeConfiguration = new MazeConfiguration();
            mazeConfiguration.height = this.height;
            mazeConfiguration.width = this.width;
            mazeConfiguration.method = this.method;
            mazeConfiguration.interval = this.interval;
            return mazeConfiguration;
        }
    }
}

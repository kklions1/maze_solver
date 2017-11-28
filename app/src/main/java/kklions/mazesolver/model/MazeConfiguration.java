package kklions.mazesolver.model;

/**
 * Class used to transfer data regarding configuration data for the maze solving screen
 *
 * Created by kliok002 on 11/28/17.
 */

public class MazeConfiguration {
    private int height;
    private int width;
    private String method;

    private MazeConfiguration(Builder builder) {
        height = builder.height;
        width = builder.width;
        method = builder.method;
    }

    public int getHeight() { return this.height; }

    public int getWidth() { return this.width; }

    public String getMethod() { return this.method; }

    public class Builder {
        private int height;
        private int width;
        private String method;


        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder withMethod(String method) {
            this.method = method;
            return this;
        }

        public MazeConfiguration build() {
            return new MazeConfiguration(this);
        }
    }
}

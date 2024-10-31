#version 150

in vec4 vertexColor;
uniform vec4 ColorModulator;
out vec4 fragColor;

void main() {
    vec4 color = vertexColor;
    if (color.a == 0.0) {
        discard;
    }
    fragColor = color * ColorModulator;
    if (all(lessThan(abs(color - vec4(1.0, 1.0, 1.0, 0.50196)), vec4(0.0001)))) {
        discard;
    }
}

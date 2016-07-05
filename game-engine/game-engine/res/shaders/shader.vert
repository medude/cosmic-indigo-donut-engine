#version 330 core

in vec3 in_Position;
in vec2 in_UV;

out vec2 pass_UV;

uniform mat4 transformation;
uniform mat4 projection;

void main(void) {
    gl_Position=projection*transformation*vec4(in_Position, 1);
    
    pass_UV=in_UV;
}
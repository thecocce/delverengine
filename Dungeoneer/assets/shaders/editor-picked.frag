#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D u_texture;
uniform vec4 u_FogColor;
uniform vec4 u_pick_color;

varying vec4 v_color;
varying vec2 v_texCoords;
varying float v_fogFactor;
varying float v_eyeDistance;

void main() {
    vec4 color;
    
    color = v_color * texture2D( u_texture, v_texCoords );
    color *= u_pick_color;
    
    if(color.a < 0.01) discard;
    
    gl_FragColor = u_pick_color;
}
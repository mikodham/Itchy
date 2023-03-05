precision mediump float;

uniform vec3 uLight, uLight2, uColor;
uniform float uTime;
uniform sampler2D uTexture0;
uniform int uIsInvincible;

varying vec3 vNormal;
varying vec3 vPosition;
varying vec2 vTexCoord;

void main() {
  vec3 tolight = normalize(uLight - vPosition);
  vec3 tolight2 = normalize(uLight2 - vPosition);
  vec3 normal = normalize(vNormal);

  float diffuse = max(0.0, dot(normal, tolight));
  diffuse += max(0.0, dot(normal, tolight2));

  vec4 preFrag = texture2D(uTexture0, vTexCoord) * diffuse;
  float inviAlpha = 0.8+sin(uTime*10.0)/2.0;
  if(uIsInvincible == 1){
    gl_FragColor = vec4(preFrag.rgb, inviAlpha);
  } else{
    gl_FragColor = vec4(preFrag.rgb, 1.0);
  }
}

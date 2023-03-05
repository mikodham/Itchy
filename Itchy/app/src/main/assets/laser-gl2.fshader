precision mediump float;

uniform vec3 uLight, uLight2, uColor;
uniform sampler2D uTexture0;
uniform float uTime;

varying vec3 vNormal;
varying vec3 vPosition;
varying vec2 vTexCoord;

void main() {
  vec3 view = normalize(vPosition);
  vec3 normal = normalize(vNormal);
  vec3 fresnel_color = vec3(0.11, 0.11, 0.11);

  vec2 scaledUv = vec2(vTexCoord.x * 6.0, vTexCoord.y * 1000.0) + uTime*10.0;
  vec3 fresnel = pow(1.0 - dot(normal, view), 3.0) * fresnel_color;
  vec4 noiseColor = texture2D(uTexture0, scaledUv);
  gl_FragColor = vec4(fresnel + noiseColor.x * uColor, noiseColor.x *1.0 + .2);
}

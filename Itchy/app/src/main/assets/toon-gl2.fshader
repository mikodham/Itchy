precision mediump float;

uniform vec3 uLight, uLight2, uColor;

varying vec3 vNormal;
varying vec3 vPosition;

void main() {
  vec3 tolight = normalize(uLight - vPosition);
  vec3 tolight2 = normalize(uLight2 - vPosition);
  vec3 normal = normalize(vNormal);
  vec3 color2;

  float diffuse = max(0.0, dot(normal, tolight));
  diffuse += max(0.0, dot(normal, tolight2));

  if (diffuse > 0.95) color2 = vec3(1.0, 1.0, 1.0);
  else if (diffuse > 0.75) color2 = vec3(0.8, 0.8, 0.8);
  else if (diffuse > 0.50) color2 = vec3(0.6, 0.6, 0.6);
  else if (diffuse > 0.25) color2 = vec3(0.4, 0.4, 0.4);
  else                       color2 = vec3(0.2, 0.2, 0.2);

  vec3 intensity = uColor * color2;

  gl_FragColor = vec4(intensity, 1.0);
}

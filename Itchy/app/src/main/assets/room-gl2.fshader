precision mediump float;

uniform vec3 uLight, uLight2, uColor;
uniform sampler2D uTexture0, uTexture1;

varying vec3 vNormal;
varying vec3 vPosition;
varying vec2 vTexCoord;
varying vec3 vModelPos;

void main() {
  vec3 tolight = normalize(uLight - vPosition);
  vec3 tolight2 = normalize(uLight2 - vPosition);
  vec3 normal = normalize(vNormal);

  float diffuse = max(0.0, dot(normal, tolight));
  diffuse += max(0.0, dot(normal, tolight2));
  vec3 intensity = uColor * diffuse;
  vec2 scaleTexCoord = vTexCoord * 15.0;

  // Ceiling
  if(vModelPos.y > 2.4){
    gl_FragColor = vec4(uColor * diffuse, 1.0);
  }
  // Wall
  else if(vModelPos.y > -2.4){
    scaleTexCoord.y *= 3.0;
    scaleTexCoord = scaleTexCoord.yx;
    gl_FragColor = texture2D(uTexture0, scaleTexCoord) * diffuse;
  }
  // Floor
  else{
    scaleTexCoord *= 1.2;
    gl_FragColor = texture2D(uTexture1, scaleTexCoord) * diffuse;
  }
}

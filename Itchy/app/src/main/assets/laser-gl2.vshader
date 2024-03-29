precision mediump float;

uniform mat4 uProjMatrix;
uniform mat4 uModelViewMatrix;
uniform mat4 uNormalMatrix;
uniform float uTime;

attribute vec3 aPosition;
attribute vec3 aNormal;
attribute vec2 aTexCoord;

varying vec3 vNormal;
varying vec3 vPosition;
varying vec2 vTexCoord;


void main() {
  vNormal = vec3(uNormalMatrix * vec4(aNormal, 0.0));
  vec4 tPosition = uModelViewMatrix * vec4(aPosition, 1.0);
  vPosition = vec3(tPosition);
  vTexCoord = vec2(aTexCoord.x, aTexCoord.y);
  gl_Position = uProjMatrix * tPosition;
}
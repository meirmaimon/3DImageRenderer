package unittests.elementsTests;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

import java.util.Arrays;

/**
 * Testing basic shadows
 *
 * @author Dan
 */
public class ShadowTests {
    private Scene scene = new Scene("Test scene");
    private Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setViewPlaneSize(200, 200).setDistance(1000);

    /**
     * Produce a picture of a sphere and triangle with point light and shade
     */
    @Test
    public void sphereTriangleInitial() {
        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, -200), 60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Triangle(new Point3D(-70, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point3D(-100, -100, 200), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("shadowSphereTriangleInitial", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a Sphere
     * producing a shading
     */
    @Test
    public void trianglesSphere() {
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setKs(0.8).setShininess(60)), //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setKs(0.8).setShininess(60)), //
                new Sphere(new Point3D(0, 0, -115), 30) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(700, 400, 400), new Point3D(40, 40, 115), new Vector(-1, -1, -4)) //
                        .setKl(4E-4).setKq(2E-5));

        Render render = new Render() //
                .setImageWriter(new ImageWriter("shadowTrianglesSphere", 600, 600)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void sphereTriangleInitial1() {
        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, -200), 60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Triangle(new Point3D(-70, -40, 20), new Point3D(-40, -70, 20), new Point3D(-68, -68, 16)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point3D(-100, -100, 200), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7).setSize(0.5));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("shadowSphereTriangleInitial1", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerAdvance(scene));
        render.renderImage();
        render.writeToImage();
    }

    /**
     * move the triangle
     */
    @Test
    public void sphereTriangleInitial3() {
        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, -200), 60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Triangle(new Point3D(-50, -20, 0), new Point3D(-20, -50, 0), new Point3D(-48, -48, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point3D(-100, -100, 200), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("shadowSphereTriangleInitial3", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void sphereTriangleInitial4() {
        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, -200), 60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Triangle(new Point3D(-70, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point3D(-100, -100, 135), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("shadowSphereTriangleInitial4", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void sphereTriangleInitial5() {
        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, -200), 60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Triangle(new Point3D(-70, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point3D(-80, -80, 80), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("shadowSphereTriangleInitial5", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void multipleObjects() {
        scene.geometries.add(//
                new Triangle(new Point3D(-40, -40, -160), new Point3D(40, -40, -160), new Point3D(0, -100, -90)) //
                        .setMaterial(new Material().setKs(0.8).setShininess(60).setKr(0.8)).setEmission(new Color((java.awt.Color.ORANGE))), //
                new Triangle(new Point3D(40, -40, -160), new Point3D(40, 40, -160), new Point3D(100, 0, -90)) //
                        .setMaterial(new Material().setKs(0.8).setShininess(60).setKr(0.8)).setEmission(new Color(java.awt.Color.ORANGE)),
                new Triangle(new Point3D(40,40,-160),new Point3D(-40,40,-160),new Point3D(0,100,-90))
                        .setMaterial(new Material().setKs(0.8).setShininess(60).setKr(0.8)).setEmission(new Color((java.awt.Color.ORANGE))),
                new Sphere(new Point3D(0, 0, -125), 20) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(50))
        //
        );
        scene.lights.add( //
                new PointLight(new Color(500, 300, 200), new Point3D(200,100,50)) //
                        .setKl(1E-8).setKq(1.5E-5));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("multipleObjects", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();

    }
    @Test
    public void multipleObjects1() {
        scene.geometries.add(//
                new Triangle(new Point3D(-40, 40, -160), new Point3D(40, -40, -160), new Point3D(-100, -100, -110)) //
                        .setMaterial(new Material().setKs(0.8).setShininess(60)).setEmission(new Color((java.awt.Color.ORANGE))), //
                new Triangle(new Point3D(40, -40, -160), new Point3D(40, 40, -160), new Point3D(100, 0, -110)) //
                        .setMaterial(new Material().setKs(0.8).setShininess(60).setKr(0.8)).setEmission(new Color(java.awt.Color.ORANGE)),
                new Triangle(new Point3D(40,40,-160),new Point3D(-40,40,-160),new Point3D(0,100,-110))
                        .setMaterial(new Material().setKs(0.8).setShininess(60).setKr(0.8)).setEmission(new Color((java.awt.Color.ORANGE))),
                new Sphere(new Point3D(0, 0, -125), 20) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.3))
                //
        );
        scene.lights.add( //
                new PointLight(new Color(500, 300, 200), new Point3D(150,100,20)) //
                        .setKl(0.00001).setKq(0.000001)  //
              //  ,new SpotLight()

        );

        Render render = new Render(). //
                setImageWriter(new ImageWriter("multipleObjects1", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();

    }

    @Test
    public void sphereTriangleSoft() {
        scene.geometries.add( //
                new Sphere(new Point3D(12, 0, -100), 20) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Triangle(new Point3D(-30, -80, -280), new Point3D(80, 40, -280), new Point3D(-95, 50, -280)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add(new PointLight(new Color(500, 300, 0), new Point3D(60, -10, 10))//
                .setKl(0.00001).setKq(0.000001).setSize(0.1));


        Render render = new Render(). //
                setImageWriter(new ImageWriter("shadowSoftTry10", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerAdvance(scene));
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void trianglesSphere1() {
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setKs(0.8).setShininess(60)), //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setKs(0.8).setShininess(60)), //
                new Sphere(new Point3D(0, 0, -115), 30) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(700, 400, 400), new Point3D(40, 40, 115), new Vector(-1, -1, -4)) //
                        .setKl(4E-4).setKq(2E-5).setSize(2.5));

        Render render = new Render() //
                .setImageWriter(new ImageWriter("shadowTrianglesSphereSoftTry", 600, 600)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerAdvance(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void sphereTriangleInitial4soft() {
        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, -200), 60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Triangle(new Point3D(-70, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point3D(-100, -100, 135), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7).setSize(0.9));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("shadowSphereTriangleInitial40soft", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerAdvance(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void softLongTime() {
        scene.geometries.add( //
                new Sphere(new Point3D(-10, -10, 0), 10) //
                        .setEmission(new Color(java.awt.Color.GRAY)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Triangle(new Point3D(-130,30,0),new Point3D(130,30,0),new Point3D(-10,20,60)) //
                        .setEmission(new Color(java.awt.Color.GRAY)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.addAll( //
                Arrays.asList(
                new PointLight(new Color(500, 300, 200), new Point3D(-50,-50,-20)) //
                        .setKl(1E-8).setKq(1.5E-5).setSize(0.5) ,
                        new SpotLight(new Color(200, 100, 100), new Point3D(-10, -150, 0), new Vector(0, 1, 0)) //
                                .setKl(1E-5).setKq(1.5E-7).setSize(0.2))
        );
        Render render = new Render(). //
                setImageWriter(new ImageWriter("softLongTime", 400, 400)) //
                .setCamera(new Camera(new Point3D(0,-300,0),new Vector(0,1,0),new Vector(0,0,1)) //
                        .setViewPlaneSize(200, 200).setDistance(700)) //
                .setRayTracer(new RayTracerAdvance(scene));
        render.renderImage();
        render.writeToImage();
    }

}

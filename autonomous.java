package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Autonomous", group = "")
public class Autonomous extends LinearOpMode {
  private DcMotor FR;
  private DcMotor FL;
  private DcMotor BL;
  private DcMotor BR;
  private DcMotor vert;
  private DcMotor rot;
  
  private Servo claw;
  private Servo duckie;
  
  private DistanceSensor cdv3_DistanceSensor;
  private ColorSensor cdv3;
  
  @Override
  public void runOpMode() {
    double motorpos;
    double newmotorpos;
    
    cdv3_DistanceSensor = hardwareMap.get(DistanceSensor.class, "cdv3");
    cdv3 = hardwareMap.get(ColorSensor.class, "cdv3");
    FR = hardwareMap.get(DcMotor.class, "FR");
    FL = hardwareMap.get(DcMotor.class, "FL");
    BL = hardwareMap.get(DcMotor.class, "BL");
    BR = hardwareMap.get(DcMotor.class, "BR");
    vert = hardwareMap.get(DcMotor.class, "vert");
    rot = hardwareMap.get(DcMotor.class, "rot");
    duckie = hardwareMap.get(Servo.class, "duckie");
    claw = hardwareMap.get(Servo.class, "claw");
    // Put initialization blocks here.
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here
      motorpos = rot.getCurrentPosition();
      newmotorpos = motorpos - 440;
      
      claw.setPosition(0);
      sleep(1000);
      rightpos(newmotorpos);
      sleep(1000);
      l2();
      sleep(1000);
      forward(100);
      sleep(100);
      claw.setPosition(1);
      sleep(1000);
      leftpos(motorpos);
      claw.setPosition(0);
      back(100);
      
      
      
      
      
      while (opModeIsActive()) {
        
        telemetry.addData("Distance Inch", cdv3_DistanceSensor.getDistance(DistanceUnit.CM));
        telemetry.addData("red", cdv3.red());
        telemetry.addData("motorpos", newmotorpos);
        telemetry.update();
      }
    }
  }
  //Movement
  private void forward(long t) {
    BL.setPower(1);
    BR.setPower(-1);
    FL.setPower(1);
    FR.setPower(-1);
    sleep(t);
    stopmove();
  }
  private void back(long t) {
    BL.setPower(-1);
    BR.setPower(1);
    FL.setPower(-1);
    FR.setPower(1);
    sleep(t);
    stopmove();
  }
  private void left() {
    BL.setPower(-1);
    BR.setPower(-1);
    FL.setPower(-1);
    FR.setPower(-1);
  }
  private void right() {
    BL.setPower(1);
    BR.setPower(1);
    FL.setPower(1);
    FR.setPower(1);
  }
  private void strafeleft() {
    BL.setPower(-1);
    BR.setPower(1);
    FL.setPower(1);
    FR.setPower(-1);
  }
  private void straferight() {
    BL.setPower(1);
    BR.setPower(-1);
    FL.setPower(-1);
    FR.setPower(1);
  }
  private void stopmove() {
    BL.setPower(0);
    BR.setPower(0);
    FL.setPower(0);
    FR.setPower(0);
  }
  //Ducks
  private void ducksgo() {
    duckie.setPosition(1);
  }
  private void ducksstop(){
    duckie.setPosition(0.5);
  }
  //Vertical Motion
  private void up(){
    vert.setPower(-0.5);
  }
  private void down(){
    vert.setPower(0.5);
  }
  private void stopvert(){
    vert.setPower(0);
  }
  private void l2(){
    up();
    sleep(450);
    stopvert();
  }
  private void l3(){
    up();
    sleep(1500);
    stopvert();
  }
  //Rotation
  private void rightpos(double nmp){
    while(rot.getCurrentPosition() > nmp && opModeIsActive()){
      rot.setPower(-0.2);
    }
    rot.setPower(0);
  }
    private void leftpos(double nmp){
    while(rot.getCurrentPosition() < nmp && opModeIsActive()){
      rot.setPower(0.2);
    }
    rot.setPower(0);
  }
  
}
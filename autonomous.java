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
    int pos = 1;
    int level = 0;
    
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
      newmotorpos = motorpos - 470;
      
      if (pos == 1){
        forward(200);
        sleep(100);
        right(580);
        sleep(100);
        back(490);
        sleep(1000);
        ducksgo();
        sleep(5600);
        ducksstop();
        sleep(200);
        forward(230);
        sleep(100);
        left(600);
        sleep(100);
        forward(125);
        sleep(500);
        if(cdv3_DistanceSensor.getDistance(DistanceUnit.CM) < 3.8){
          level = 1;
        }
        sleep(200);
        straferight(300);
        sleep(200);
        if(cdv3_DistanceSensor.getDistance(DistanceUnit.CM) < 3.8){
          level = 2;
        }
        sleep(200);
        straferight(300);
        sleep(200);
        sleep(100);
        if(cdv3_DistanceSensor.getDistance(DistanceUnit.CM) < 3.8){
          level = 3;
        }
        sleep(200);
        straferight(480);
        sleep(500);
        if(level == 1){
          straferight(250);
          sleep(100);
          rightpos(newmotorpos);
          sleep(500);
          forward(100);
          sleep(200);
          claw.setPosition(1);
          sleep(3000);
          claw.setPosition(0.5);
        }else if(level == 2){
          l2();
          sleep(1000);
          straferight(250);
          sleep(100);
          rightpos(newmotorpos);
          sleep(500);
          forward(100);
          sleep(200);
          claw.setPosition(1);
          sleep(3000);
          claw.setPosition(0.5);
        }else if (level == 3){
          l3();
          sleep(200);
          straferight(250);
          sleep(100);
          rightpos(newmotorpos);
          sleep(500);
          forward(150);
          sleep(200);
          claw.setPosition(1);
          sleep(3000);
          claw.setPosition(0.5);
        }
        back(300);
        left(500);
        forward(1200);
        ducksstop();
        
      }else if(pos == 4){
        forward(150);
        sleep(100);
        straferight(900);
        duckie.setPosition(1);
        sleep(5800);
        ducksstop();
        forward(500);
      
        
      }
      
      
      
      
      
      while (opModeIsActive()) {
        
        telemetry.addData("Distance Inch", cdv3_DistanceSensor.getDistance(DistanceUnit.CM));
        telemetry.addData("red", cdv3.red());
        telemetry.addData("level", level);
        telemetry.addData("motorpos", newmotorpos);
        telemetry.update();
      }
    }
  }
  //Movement
  private void forward(long t) {
    BL.setPower(0.5);
    BR.setPower(-0.5);
    FL.setPower(0.5);
    FR.setPower(-0.5);
    sleep(t);
    stopmove();
  }
  private void back(long t) {
    BL.setPower(-0.5);
    BR.setPower(0.5);
    FL.setPower(-0.5);
    FR.setPower(0.5);
    sleep(t);
    stopmove();
  }
  private void left(long t) {
    BL.setPower(-0.5);
    BR.setPower(-0.5);
    FL.setPower(-0.5);
    FR.setPower(-0.5);
    sleep(t);
    stopmove();
  }
  private void right(long t) {
    BL.setPower(0.5);
    BR.setPower(0.5);
    FL.setPower(0.5);
    FR.setPower(0.5);
    sleep(t);
    stopmove();
  }
  private void strafeleft(long t) {
    BL.setPower(-0.5);
    BR.setPower(0.5);
    FL.setPower(0.5);
    FR.setPower(-0.5);
    sleep(t);
    stopmove();
    
  }
  private void straferight(long t) {
    BL.setPower(0.5);
    BR.setPower(-0.5);
    FL.setPower(-0.5);
    FR.setPower(0.5);
    sleep(t);
    stopmove();
  }
  private void stopmove() {
    BL.setPower(0);
    BR.setPower(0);
    FL.setPower(0);
    FR.setPower(0);
  }
  //Ducks
  private void ducksgo() {
    duckie.setPosition(0);
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
    sleep(550);
    stopvert();
  }
  private void l3(){
    up();
    sleep(2000);
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
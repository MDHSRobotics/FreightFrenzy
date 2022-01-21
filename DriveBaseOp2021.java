
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.configuration.annotations.DigitalIoDeviceType;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "DriveBaseOp2021", group = "")
public class DriveBaseOp2021 extends LinearOpMode {

  private DcMotor FR;
  private DcMotor FL;
  private DcMotor BL;
  private DcMotor BR;
  private Servo duckie;
  private DcMotor rot;
  private DcMotor vert;
  private DcMotor intake;
  private Servo claw;
  private DistanceSensor frontsensor;

  //private DistanceSensor cdv3_DistanceSensor;
  //private ColorSensor cdv3;
  
  private DigitalChannel front;
  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    float drivey;
    float drivex;
    double right;
    double left;
    float pos;
    double mult = 0.5;

    FR = hardwareMap.get(DcMotor.class, "FR");
    FL = hardwareMap.get(DcMotor.class, "FL");
    BL = hardwareMap.get(DcMotor.class, "BL");
    BR = hardwareMap.get(DcMotor.class, "BR");
    duckie = hardwareMap.get(Servo.class, "duckie");
    rot = hardwareMap.get(DcMotor.class, "rot");
    vert = hardwareMap.get(DcMotor.class, "vert");
    claw = hardwareMap.get(Servo.class, "claw");
    intake = hardwareMap.get(DcMotor.class, "intake");
    front = hardwareMap.get(DigitalChannel.class,"Front");
    frontsensor = hardwareMap.get(DistanceSensor.class, "frontsensor");
    //cdv3_DistanceSensor = hardwareMap.get(DistanceSensor.class, "cdv3");
    //cdv3 = hardwareMap.get(ColorSensor.class, "cdv3");
    

    // Put initialization blocks here.
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // Drive
        if(gamepad1.b){
          mult = 0.7;
        }else if (gamepad1.x){
          mult = 0.2;
        }else if (gamepad1.y){
          mult = 0.5;
        }
        
        
        drivey = gamepad1.left_stick_y;
        drivex = gamepad1.left_stick_x;
        right = drivey + drivex;
        left = drivex - drivey;
        FR.setPower(right * mult);
        FL.setPower(left * mult);
        BL.setPower(left * mult);
        BR.setPower(right * mult);
        //Stafe
        if (gamepad1.left_bumper) {
          BL.setPower(-1);
          BR.setPower(1);
          FL.setPower(1);
          FR.setPower(-1);
        } else if (gamepad1.right_bumper) {
          BL.setPower(1);
          BR.setPower(-1);
          FL.setPower(-1);
          FR.setPower(1);
        }
        //duckie
        if(gamepad2.right_trigger > 0){
          duckie.setPosition(1);
        }else if (gamepad2.left_trigger > 0){
          duckie.setPosition(-1);
        }else{
          duckie.setPosition(0.5);
        }
        //rotation
        pos = rot.getCurrentPosition();
        rot.setPower(-gamepad2.left_stick_x * 0.5);
        //vertical
        vert.setPower(gamepad2.left_stick_y);
        //claw
        if(gamepad2.x){
          claw.setPosition(1);
        }else if(gamepad2.b){
          claw.setPosition(-1);
        }else if(gamepad2.y){
          claw.setPosition(0.5);
          
        }
        if(gamepad1.right_trigger > 0){
          intake.setPower(1);
        }else if (gamepad1.left_trigger > 0){
          intake.setPower(-1);
        }else{
          intake.setPower(0);
        }
        
        telemetry.addData("motor", rot.getCurrentPosition());
        telemetry.addData("motor2",BL.getCurrentPosition());
        telemetry.addData("motor3",BR.getCurrentPosition());
        telemetry.addData("motor4",FL.getCurrentPosition());
        telemetry.addData("motor5",FR.getCurrentPosition());
        telemetry.addData("frontsensor", frontsensor.getDistance(DistanceUnit.CM));
        telemetry.update();
      }
    }
  }
  

  
  
  
}
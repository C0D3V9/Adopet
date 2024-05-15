import { IsNotEmpty, IsNumber, IsOptional, IsString } from "class-validator"

export class PetDto{
  @IsNotEmpty()
  @IsString()
  name:string
  @IsNotEmpty()
  @IsString()
  species:string
  @IsNotEmpty()
  @IsNumber()
  age:number
  @IsOptional()
  @IsString()
  description:string
  @IsNotEmpty()
  @IsString()
  imageUrl:string
  @IsOptional()
  @IsNumber()
  weight:number
  @IsOptional()
  @IsString()
  sex:string  
}
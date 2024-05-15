import {  Body, Controller, Delete, Get, Param, Post } from '@nestjs/common';
import { PetService } from './pet.service';
import { PetDto } from './dto';

@Controller('pet')
export class PetController {
    constructor(private petservice:PetService){
    }
    @Get()
    getall(){
        return this.petservice.getall()
    } 
    @Get(':id')
    findOne(@Param('id')id:number){
        return this.petservice.getPetById(id);
    }
    @Delete(':id')
    deletOne(@Param('id')id:number){
        return this.petservice.deletePetById(id);
    }
    @Post('/add')
    addpet(@Body()dto:PetDto,){
        return this.petservice.addpet(dto);
    }
}

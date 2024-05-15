import { ForbiddenException, Injectable } from '@nestjs/common';
import { PrismaService } from 'src/prisma/prisma.service';
import { PetDto } from './dto';

@Injectable()
export class PetService {
    constructor(private prisma:PrismaService){}
  async  getall(){
        return await this.prisma.pet.findMany();
    }
    async getPetById(id: number) {
      try {
          // Find a pet by its ID
          const petId = typeof id === 'string' ? parseInt(id, 10) : id;
          const pet = await this.prisma.pet.findUnique({
              where: { id: petId }
          });
          return pet; // Return the found pet or null if not found
      } catch (error) {
          console.error(`Error fetching pet with ID ${id}:`, error);
          throw error;
      }
  }
  
  async deletePetById(id: number) {
      try {
          // Delete a pet by its ID
          const petId = typeof id === 'string' ? parseInt(id, 10) : id;
          const deletedPet = await this.prisma.pet.delete({
              where: { id: petId }
          });
          return deletedPet; // Return the deleted pet
      } catch (error) {
          console.error(`Error deleting pet with ID ${id}:`, error);
          throw error;
      }
  }
  async addpet(dto:PetDto,){
    const age = typeof dto.age === 'string' ? parseInt(dto.age, 10) : dto.age;
    try {

      const newpet=await this.prisma.pet.create({
        data :{
          ownerId:1,
          name:dto.name,
          age:age,
          imageUrl:dto.imageUrl,
          description:dto.description,
          species:dto.species
        }
        
      });
      return {newpet}
    } catch (error) {
      throw new ForbiddenException("there is an error ${error}");
    }
  }
}

<template>
  
  <div class="container">
    <div class="row">
      <div class="col-12 mt-5">
        <SearchComp  @emitSearch="searchPizza"/>
      </div>

      <div class="col-10 m-auto py-5 my-5">

        <div v-if="!pizzaCreateForm">
          <button class="btn btn-success" @click="pizzaCreateForm = true">Add</button>
        </div>
        <div v-else>
          <div v-if="errorCreateCheck">
            {{ errorCreate }}
          </div>
            <form @submit.prevent="createPizza">

              <div>
                <label for="name">Name</label>
                <br>
                <input type="text" name="name" v-model="pizzaCreate.name">
              </div>
   
              <div>
                <label for="description">Description</label>
                <br>
                <input type="text" name="description" v-model="pizzaCreate.description">
              </div>
          
              <div class="mb-3">
                <label for="price">Price</label>
                <br>
                <input type="number" name="price" v-model="pizzaCreate.price">
              </div>
             
              <button class="btn btn-success mx-2" type="submit">Create</button>
              <button class="btn btn-danger" @click="pizzaCreateForm  = false">Cancel</button>

          </form>
        </div>

        <table class="table">
        <thead>
          <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
            <th scope="col">Price</th>
            <th scope="col">Ingredients</th>
            <th scope="col">Actions</th>
          </tr>
        </thead>

          <tbody v-if="pizzeCheck">

            <tr v-for="pizza in pizzas" :key="pizza.id">
              <div v-if="isEditing != pizza.id">
                <th scope="row">{{ pizza.id }}</th>
                <td>{{ pizza.name }}</td>
                <td>{{ pizza.description }}</td>
                <td>{{ pizza.price }} $ </td>
              </div>
              <div v-else>
                <form @submit.prevent="updatePizza()">
                    <label for="name">Name</label>
                    <input type="text" name="name" v-model="pizza.name">
                    <br> 
                    <label for="description">Description</label>
                    <input type="text" name="description" v-model="pizza.description">
                    <br>
                    <label for="price">Price</label>
                    <input type="number" name="price" v-model="pizza.price">
                    <br><br>
                    <button type="submit" class="btn btn-success">Update</button>
                    <button @click="isEditingPizza(-1)" class="btn btn-warning">Cancel</button>
                  </form>
              </div>

              <td>
                <div v-if="pizza.ingredients">
                  <span v-for="i in pizza.ingredients" :key="i.id">{{ i.name }} </span>                
                </div>
                <div v-else>
                  <button class="btn btn-primary" @click="getIngredients(pizza.id)">Get Ingredients</button>
                </div>
              </td>

              <td>
                <button class="btn btn-success me-2" @click="isEditingPizza(pizza.id)">Edit</button>
                <button class="btn btn-danger" @click="deletePizza(pizza.id)">Delete</button>
              </td>
            </tr>

          </tbody>
          <div v-else>
            <h5 class="py-5">
              Non ci sono pizze.
            </h5>
          </div>
      </table>
      </div>
    </div>

    <div v-if="ingredientCheck"></div>

  </div>

</template>

<script >
import axios from 'axios';
import SearchComp from './searchComp.vue';
const HTTP = "http://localhost:8080/api/1/pizza/";

export default {
  name: 'HelloWorld',
  components:{
    SearchComp,
  },
  data(){
    return{
      pizzas: {},
      pizzeCheck: false,
      ingredientCheck: false,
      pizzaCreateForm: false,
      pizzaCreate: {},
      errorCreateCheck: false,
      errorCreate: "",
      isEditing: -1,
    }
  },
  methods:{

    searchPizza(query){
      axios.get(HTTP + "search" , {params: {
        needle: query
      }})
      .then(res =>{
        console.log(res.data)
        this.pizzas = res.data
      })
    },

    getPizzas(){
      axios.get(HTTP + "all")
      .then((response) =>{
        this.pizzas = response.data;

        if(response.data.length > 0){
          this.pizzeCheck = true; 
        }

        console.log(response.data)
      })
      .catch((e) => console.log(e))
    },
     getIngredients(id) {
      axios.get(HTTP + 'ingredient/' + id)
           .then(res => {
            this.ingredientCheck = true; 
            console.log(res.data)

            const pizzaIndex = this.getPizzaIndex(id)

            this.pizzas[pizzaIndex].ingredients = res.data;

            console.log(this.pizzas[pizzaIndex].ingredients)
             this.ingredientCheck = false; 
      });

    },
    getPizzaIndex(id){

      for (let i=0;i < this.pizzas.length; i++) {
        const pizza = this.pizzas[i];
        if (pizza.id == id)
          return i;
      }
      return -1;
    },

    createPizza(){

      axios.post(HTTP + "store", this.pizzaCreate)
      .then(res=>{

        console.log(res)

        const pizza = res.data

        if(pizza[1]){
          this.pizzas.push(pizza[0])
          this.pizzaCreate = {}
          this.this.errorCreateCheck = false
          this.errorCreate = ""
        }else{
          this.errorCreateCheck = true
          this.errorCreate = pizza[2]
        }


      })

    },

    isEditingPizza(id){
      this.isEditing = id;
    },

    getPizzaById(id){
      return this.pizzas[this.getPizzaIndex(id)]
    },

    updatePizza(){

      const id = this.isEditing
      const pizza = this.getPizzaById(id)

      axios.post(HTTP + "update/" + id, pizza)
      .then(res =>{
        this.isEditing = -1
        console.log(res)
      })
    },


    deletePizza(id){
      axios.get(HTTP + "delete/" + id)
      .then(res => {
        console.log(res.data)

        this.pizzas.splice(this.getPizzaIndex(id), 1)
      })
    }
  },
  created(){
    this.getPizzas();
  }
}
</script>

<style scoped>
@import "bootstrap";
</style>

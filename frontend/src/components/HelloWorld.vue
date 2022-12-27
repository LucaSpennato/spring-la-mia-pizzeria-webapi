<template>
  
  <div class="container">
    <div class="row">

      <div class="col-10 m-auto pt-5 pb-2">

        <div v-if="!pizzaCreateForm">
          <button class="btn btn-success" @click="pizzaCreateForm = true">Add a new Pizza</button>
        </div>
        <div v-else class="row">
          <div v-if="errorCreateCheck">
            {{ errorCreate }}
          </div>
            <form @submit.prevent="createPizza" class="col-5 m-4">   
              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">Name</span>
                <input type="text" class="form-control" name="name" v-model="pizzaCreate.name">
              </div>

              <div class="input-group mb-3">
                <span class="input-group-text">$</span>
                <input type="text" class="form-control" name="price" v-model="pizzaCreate.price">
                <span class="input-group-text">.00</span>
              </div>

              <div class="input-group">
                <span class="input-group-text">Description</span>
                <textarea class="form-control" v-model="pizzaCreate.description"></textarea>
              </div>

             
              <div class="mt-3">
                <button class="btn btn-success mx-2" type="submit">Create</button>
                <button class="btn btn-danger" @click="pizzaCreateForm = false">Cancel</button>
              </div>

          </form>
        </div>
      <div class="col-12 mt-5">
        <SearchComp  @emitSearch="searchPizza"/>
      </div>

      <div v-if="pizzeCheck">
       <div class="row">
        <div class="card m-3 col-5" v-for="pizza in pizzas" :key="pizza.id">
          <div class="card-body" v-if="isEditing != pizza.id">
            <h5 class="text-capitalize card-title">{{ pizza.name }}</h5>
            <h5 class="text-capitalize card-title">{{ pizza.price }} $</h5>
            <h6 class="text-capitalize card-subtitle mb-2 text-muted">
              <div v-if="pizza.ingredients">
                <span v-for="i in pizza.ingredients" :key="i.id">{{ i.name }} </span>                
              </div>
              <div v-else>
                <button class="btn btn-primary" @click="getIngredients(pizza.id)">Get Ingredients</button>
              </div>
            </h6>
            <p class="card-text">{{ pizza.description }}</p>
            <button class="btn btn-success me-2" @click="isEditingPizza(pizza.id)">Edit</button>
            <button class="btn btn-danger" @click="deletePizza(pizza.id)">Delete</button>
          </div>

          <div class="card-body" v-else>
            <form @submit.prevent="updatePizza()">
              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">Name</span>
                <input type="text" class="form-control" name="name" v-model="pizza.name">
              </div>

              <div class="input-group mb-3">
                <span class="input-group-text">$</span>
                <input type="text" class="form-control" name="price" v-model="pizza.price">
                <span class="input-group-text">.00</span>
              </div>

              <div class="input-group">
                <span class="input-group-text">Description</span>
                <textarea class="form-control" v-model="pizza.description"></textarea>
              </div>
           
              <div class="mt-4">
                <button type="submit" class="btn btn-success me-2">Update</button>
                <button @click="isEditingPizza(-1)" class="btn btn-warning">Cancel</button>
              </div>
            </form>
          </div>
        </div>
       </div>
      </div>
      <div v-else>
        Non ci sono pizze.
      </div>
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

<template>
  
  <div class="container">
    <div class="row">
      <div class="col-10 m-auto py-5 my-5">
        <table class="table">
        <thead>
          <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
            <th scope="col">Price</th>
          </tr>
        </thead>
        <tbody v-if="loader">
          <tr v-for="pizza in pizzas" :key="pizza.id">
            <th scope="row">{{ pizza.id }}</th>
            <td>{{ pizza.name }}</td>
            <td>{{ pizza.description }}</td>
            <td>{{ pizza.price }} $ </td>
            <td>{{ pizza.ingredients }}</td>
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
  </div>

</template>

<script>
import axios from 'axios';

export default {
  name: 'HelloWorld',
  data(){
    return{
      pizzas: {},
      loader: false,
    }
  },
  methods:{
    getPizzas(){
      axios.get("http://localhost:8080/api/1/pizza/all")
      .then((response) =>{
        this.pizzas = response.data;
        this.loader = false;
        if(response.data.length > 0){
          this.loader = true;
        }
        console.log(response);
      })
      .catch((e) => console.log(e))
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

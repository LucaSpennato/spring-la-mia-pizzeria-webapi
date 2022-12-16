<template>
  
  <div class="row">
    <div class="col-6 m-auto py-5 my-5">
      <ul class="list-group">
        <li class="list-group-item" v-for="pizza in pizzas" :key="pizza.id">
          {{ pizza.id }} - {{ pizza.name }} - {{ pizza.description }} - {{ pizza.price }} $
        </li>
    </ul>

    </div>
  </div>

</template>

<script>
import axios from 'axios';

export default {
  name: 'HelloWorld',
  data(){
    return{
      pizzas: '',
    }
  },
  methods:{
    getPizzas(){
      axios.get("http://localhost:8080/api/1/pizza")
      .then((response) =>{
        this.pizzas = response.data;
        console.log(this.pizzas);

      })
    }
  },
  created(){
    this.getPizzas();
  }
}
</script>

<style scoped>
@import "bootstrap"
</style>

<template>

    <div class="input-group mb-3">
        <button class="btn btn-outline-success" type="button" id="button-addon1" @click="emitSearch()">Search</button>
        <input type="text" class="form-control" v-model="needle">
    </div>

</template>

<script>
export default {
    name: "SearchComp",
    data(){
        return{
            needle: "",
        }
    },
    methods:{
        emitSearch(){
            this.$emit("emitSearch", this.needle);
        }
    }
}
</script>

<style>

</style>
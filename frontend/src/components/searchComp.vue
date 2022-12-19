<template>
    <div>
        <input v-model="needle">
        <button class="btn btn-primary mx-3" @click="emitSearch()">Search</button>
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
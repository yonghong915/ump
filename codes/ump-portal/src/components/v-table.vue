<template>
    <div class="table">
        <table border="1px" v-dragform>
            <thead>
                <tr>
                   <th></th>
                   <th>
                      <input type="checkbox" v-if="checkboxs" v-model='checked' v-on:click='checkAll' class="chestyle" />
                   </th>

                </tr>
            </thead>
        </table>
    </div>
<template>


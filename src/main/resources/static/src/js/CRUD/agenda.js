

import { createItem, readItems, readItem, updateItem, deleteItem } from './crud';

const serviciosEndpoint = "/agenda";

export const nuevoServicio = async servicio => createItem(serviciosEndpoint, servicio);
export const obtenerServicios = async () => readItems(serviciosEndpoint);
export const obtenerServicio = async id => readItem(serviciosEndpoint, id);
export const editarServicio = async servicio => updateItem(serviciosEndpoint, servicio);
export const eliminarServicio = async id => deleteItem(serviciosEndpoint, id);

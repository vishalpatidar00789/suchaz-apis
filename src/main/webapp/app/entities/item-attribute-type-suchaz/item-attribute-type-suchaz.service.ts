import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { ItemAttributeTypeSuchaz } from './item-attribute-type-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<ItemAttributeTypeSuchaz>;

@Injectable()
export class ItemAttributeTypeSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/item-attribute-types';

    constructor(private http: HttpClient) { }

    create(itemAttributeType: ItemAttributeTypeSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(itemAttributeType);
        return this.http.post<ItemAttributeTypeSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(itemAttributeType: ItemAttributeTypeSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(itemAttributeType);
        return this.http.put<ItemAttributeTypeSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ItemAttributeTypeSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<ItemAttributeTypeSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<ItemAttributeTypeSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ItemAttributeTypeSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ItemAttributeTypeSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ItemAttributeTypeSuchaz[]>): HttpResponse<ItemAttributeTypeSuchaz[]> {
        const jsonResponse: ItemAttributeTypeSuchaz[] = res.body;
        const body: ItemAttributeTypeSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to ItemAttributeTypeSuchaz.
     */
    private convertItemFromServer(itemAttributeType: ItemAttributeTypeSuchaz): ItemAttributeTypeSuchaz {
        const copy: ItemAttributeTypeSuchaz = Object.assign({}, itemAttributeType);
        return copy;
    }

    /**
     * Convert a ItemAttributeTypeSuchaz to a JSON which can be sent to the server.
     */
    private convert(itemAttributeType: ItemAttributeTypeSuchaz): ItemAttributeTypeSuchaz {
        const copy: ItemAttributeTypeSuchaz = Object.assign({}, itemAttributeType);
        return copy;
    }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { ItemImageSuchaz } from './item-image-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<ItemImageSuchaz>;

@Injectable()
export class ItemImageSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/item-images';

    constructor(private http: HttpClient) { }

    create(itemImage: ItemImageSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(itemImage);
        return this.http.post<ItemImageSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(itemImage: ItemImageSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(itemImage);
        return this.http.put<ItemImageSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ItemImageSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<ItemImageSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<ItemImageSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ItemImageSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ItemImageSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ItemImageSuchaz[]>): HttpResponse<ItemImageSuchaz[]> {
        const jsonResponse: ItemImageSuchaz[] = res.body;
        const body: ItemImageSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to ItemImageSuchaz.
     */
    private convertItemFromServer(itemImage: ItemImageSuchaz): ItemImageSuchaz {
        const copy: ItemImageSuchaz = Object.assign({}, itemImage);
        return copy;
    }

    /**
     * Convert a ItemImageSuchaz to a JSON which can be sent to the server.
     */
    private convert(itemImage: ItemImageSuchaz): ItemImageSuchaz {
        const copy: ItemImageSuchaz = Object.assign({}, itemImage);
        return copy;
    }
}

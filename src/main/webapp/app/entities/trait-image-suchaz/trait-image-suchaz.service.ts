import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { TraitImageSuchaz } from './trait-image-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<TraitImageSuchaz>;

@Injectable()
export class TraitImageSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/trait-images';

    constructor(private http: HttpClient) { }

    create(traitImage: TraitImageSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(traitImage);
        return this.http.post<TraitImageSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(traitImage: TraitImageSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(traitImage);
        return this.http.put<TraitImageSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<TraitImageSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<TraitImageSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<TraitImageSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<TraitImageSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: TraitImageSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<TraitImageSuchaz[]>): HttpResponse<TraitImageSuchaz[]> {
        const jsonResponse: TraitImageSuchaz[] = res.body;
        const body: TraitImageSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to TraitImageSuchaz.
     */
    private convertItemFromServer(traitImage: TraitImageSuchaz): TraitImageSuchaz {
        const copy: TraitImageSuchaz = Object.assign({}, traitImage);
        return copy;
    }

    /**
     * Convert a TraitImageSuchaz to a JSON which can be sent to the server.
     */
    private convert(traitImage: TraitImageSuchaz): TraitImageSuchaz {
        const copy: TraitImageSuchaz = Object.assign({}, traitImage);
        return copy;
    }
}

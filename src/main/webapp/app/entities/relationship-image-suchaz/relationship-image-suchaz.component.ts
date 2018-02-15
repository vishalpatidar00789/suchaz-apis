import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { RelationshipImageSuchaz } from './relationship-image-suchaz.model';
import { RelationshipImageSuchazService } from './relationship-image-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-relationship-image-suchaz',
    templateUrl: './relationship-image-suchaz.component.html'
})
export class RelationshipImageSuchazComponent implements OnInit, OnDestroy {
relationshipImages: RelationshipImageSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private relationshipImageService: RelationshipImageSuchazService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.relationshipImageService.query().subscribe(
            (res: HttpResponse<RelationshipImageSuchaz[]>) => {
                this.relationshipImages = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInRelationshipImages();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: RelationshipImageSuchaz) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInRelationshipImages() {
        this.eventSubscriber = this.eventManager.subscribe('relationshipImageListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}

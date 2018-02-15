import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { TraitImageSuchaz } from './trait-image-suchaz.model';
import { TraitImageSuchazService } from './trait-image-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-trait-image-suchaz',
    templateUrl: './trait-image-suchaz.component.html'
})
export class TraitImageSuchazComponent implements OnInit, OnDestroy {
traitImages: TraitImageSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private traitImageService: TraitImageSuchazService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.traitImageService.query().subscribe(
            (res: HttpResponse<TraitImageSuchaz[]>) => {
                this.traitImages = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInTraitImages();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: TraitImageSuchaz) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInTraitImages() {
        this.eventSubscriber = this.eventManager.subscribe('traitImageListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}

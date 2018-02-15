import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { HobbyImageSuchaz } from './hobby-image-suchaz.model';
import { HobbyImageSuchazService } from './hobby-image-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-hobby-image-suchaz',
    templateUrl: './hobby-image-suchaz.component.html'
})
export class HobbyImageSuchazComponent implements OnInit, OnDestroy {
hobbyImages: HobbyImageSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private hobbyImageService: HobbyImageSuchazService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.hobbyImageService.query().subscribe(
            (res: HttpResponse<HobbyImageSuchaz[]>) => {
                this.hobbyImages = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInHobbyImages();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: HobbyImageSuchaz) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInHobbyImages() {
        this.eventSubscriber = this.eventManager.subscribe('hobbyImageListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}

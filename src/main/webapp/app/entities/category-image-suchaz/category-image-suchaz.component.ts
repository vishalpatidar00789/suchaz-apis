import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { CategoryImageSuchaz } from './category-image-suchaz.model';
import { CategoryImageSuchazService } from './category-image-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-category-image-suchaz',
    templateUrl: './category-image-suchaz.component.html'
})
export class CategoryImageSuchazComponent implements OnInit, OnDestroy {
categoryImages: CategoryImageSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private categoryImageService: CategoryImageSuchazService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.categoryImageService.query().subscribe(
            (res: HttpResponse<CategoryImageSuchaz[]>) => {
                this.categoryImages = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCategoryImages();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CategoryImageSuchaz) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInCategoryImages() {
        this.eventSubscriber = this.eventManager.subscribe('categoryImageListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}

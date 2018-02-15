import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { CategoryImageSuchaz } from './category-image-suchaz.model';
import { CategoryImageSuchazService } from './category-image-suchaz.service';

@Component({
    selector: 'jhi-category-image-suchaz-detail',
    templateUrl: './category-image-suchaz-detail.component.html'
})
export class CategoryImageSuchazDetailComponent implements OnInit, OnDestroy {

    categoryImage: CategoryImageSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private categoryImageService: CategoryImageSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCategoryImages();
    }

    load(id) {
        this.categoryImageService.find(id)
            .subscribe((categoryImageResponse: HttpResponse<CategoryImageSuchaz>) => {
                this.categoryImage = categoryImageResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCategoryImages() {
        this.eventSubscriber = this.eventManager.subscribe(
            'categoryImageListModification',
            (response) => this.load(this.categoryImage.id)
        );
    }
}

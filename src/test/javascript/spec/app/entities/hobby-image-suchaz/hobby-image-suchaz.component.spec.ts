/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { HobbyImageSuchazComponent } from '../../../../../../main/webapp/app/entities/hobby-image-suchaz/hobby-image-suchaz.component';
import { HobbyImageSuchazService } from '../../../../../../main/webapp/app/entities/hobby-image-suchaz/hobby-image-suchaz.service';
import { HobbyImageSuchaz } from '../../../../../../main/webapp/app/entities/hobby-image-suchaz/hobby-image-suchaz.model';

describe('Component Tests', () => {

    describe('HobbyImageSuchaz Management Component', () => {
        let comp: HobbyImageSuchazComponent;
        let fixture: ComponentFixture<HobbyImageSuchazComponent>;
        let service: HobbyImageSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [HobbyImageSuchazComponent],
                providers: [
                    HobbyImageSuchazService
                ]
            })
            .overrideTemplate(HobbyImageSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(HobbyImageSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HobbyImageSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new HobbyImageSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.hobbyImages[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
